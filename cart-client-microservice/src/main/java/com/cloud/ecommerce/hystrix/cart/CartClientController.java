package com.cloud.ecommerce.hystrix.cart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.nikhu.ecommerce.cart.Cart;
import com.nikhu.ecommerce.cart.CartItem;


@RestController
public class CartClientController extends ResponseEntityExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(CartClientController.class);
	
	@RequestMapping("/")
    public String index() {
        return "<h1>Welcome to Cart Client API!</h1>";
    }
	
	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "getCartFallBack", 
					groupKey = "getCartGroup",
					threadPoolKey = "getCardThreadPool",
					commandKey = "getCartCommandKey" 
					)
	@RequestMapping(value = "/cart-client/{id}", method = RequestMethod.GET)
    public Cart cart(@PathVariable("id") String id) {
        log.debug("Received request for cart client by id: {}", id);
        //Below service hard coding needs to be injected through property
        //Here note cart-microservice is the actual service registered with Eureka.
        String cartMsURL = "http://cart-microservice/cart/" +id+ "";
        Cart cart = restTemplate.getForObject(cartMsURL, Cart.class);
        log.debug("Returned Cart from cart client: {}", cart);
        return cart;
    }
	
	public Cart getCartFallBack(String id) {
		System.out.println("Returning cart from fall back get");
		log.debug("Returning cart from fall back get");
		Cart cart = new Cart();
		cart.setId(String.valueOf(0));
		cart.setItems(new ArrayList<>());
		cart.setTotal(0.0f);
		return cart;
	}
	
	@HystrixCommand(fallbackMethod = "postCartFallBack", 
			groupKey = "postCartGroup",
			threadPoolKey = "postCardThreadPool",
			commandKey = "postCartCommandKey" 
			)
	@RequestMapping(value = "/cart-client/{id}", method = RequestMethod.POST)
    public Cart cart(@PathVariable("id") String id, @RequestBody CartItem cartItem) {
        log.debug("Received request to add item to cart client by id: {}", id);
        //Below service hard coding needs to be injected through property
        //Here note cart-microservice is the actual service registered with Eureka.
        String cartMsURL = "http://cart-microservice/cart/{id}";
        
        
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("id", id);
        
        //Cart cart = restTemplate.postForObject(cartMsURL, request, Cart.class);
        Cart cart = restTemplate.postForObject(cartMsURL, cartItem, Cart.class, paramMap);
        
        //Cart cart = cartRepository.addToCart(id, cartItem);
        log.debug("Returned Cart from cart client: {}", cart);
        return cart;
    }
	
	public Cart postCartFallBack(String id, CartItem cartItem) {
		System.out.println("Returning cart from fall back POST");
		log.debug("Returning cart from fall back POST");
		Cart cart = new Cart();
		cart.setId(String.valueOf(0));
		cart.setItems(new ArrayList<>());
		cart.setTotal(0.0f);
		return cart;
	}

    @RequestMapping(value = "/cart-client", method = RequestMethod.POST)
    public Cart cart(@RequestBody CartItem cartItem) {
        log.debug("Received request to add item to cart client without id.");
        //Below service hard coding needs to be injected through property.
        //Here note cart-microservice is the actual service registered with Eureka.
        String cartMsURL = "http://cart-microservice/cart";
        HttpEntity<CartItem> request = new HttpEntity<>(cartItem);
        
        Cart cart = restTemplate.postForObject(cartMsURL, request, Cart.class);
        //Cart cart = cartRepository.addToCart(null, cartItem);
        log.debug("Returned Cart from cart client: {}", cart);
        return cart;
    }

    @ExceptionHandler(Exception.class)
    void handleExceptions(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error @ cart client ms. We will be addressing this issue soon.");
    }
}
