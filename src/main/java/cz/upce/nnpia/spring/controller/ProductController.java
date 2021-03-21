package cz.upce.nnpia.spring.controller;

import cz.upce.nnpia.spring.dto.AddOrEditProductDto;
import cz.upce.nnpia.spring.entity.Product;
import cz.upce.nnpia.spring.repository.ProductRepository;
import cz.upce.nnpia.spring.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FileService fileService;

    @ExceptionHandler(RuntimeException.class)
    public String handleException(){
        return "error";
    }

    @GetMapping("/")
    public String showAllProducts(Model model){
        model.addAttribute("productList", productRepository.findAll());
        return "product-list";
    }

    @GetMapping("/product-detail/{id}")
    public String showProductDetail(@PathVariable(required = false) Long id, Model model){
        model.addAttribute("product", productRepository.findById(id).get());
        return "product-detail";
    }

    @GetMapping(value = {"/product-form", "/product-form/{id}"})
    public String showProductForm(@PathVariable(required = false) Long id, Model model){
        if(id!=null){
            Product byId = productRepository.findById(id).orElse(new Product());

            AddOrEditProductDto dto = new AddOrEditProductDto();
            dto.setId(byId.getId());
            dto.setName(byId.getName());
            dto.setDescription(byId.getDescription());
            dto.setRating(byId.getRating());
            dto.setInStock(byId.getInStock());
            dto.setDisplayed(byId.getDisplayed());
            dto.setPrice(byId.getPrice());
            model.addAttribute("product",dto);
        }else{
            model.addAttribute("product", new AddOrEditProductDto());
        }
        return "product-form";
    }

    @PostMapping("/product-form-process")
    public String productFromProcess(AddOrEditProductDto addOrEditProductDto){
        Product product = new Product();
        product.setId(addOrEditProductDto.getId());
        product.setName(addOrEditProductDto.getName());
        product.setDescription(addOrEditProductDto.getDescription());
        product.setRating(addOrEditProductDto.getRating());
        product.setInStock(addOrEditProductDto.getInStock());
        product.setDisplayed(addOrEditProductDto.getDisplayed());
        product.setPrice(addOrEditProductDto.getPrice());

        MultipartFile image = addOrEditProductDto.getImage();
        if(!image.getOriginalFilename().equals("")) {
            product.setPathToImage(fileService.upload(image));
        }else{
            Optional<Product> product1 = productRepository.findById(addOrEditProductDto.getId());
            product1.ifPresent(value -> product.setPathToImage(value.getPathToImage()));
        }
        productRepository.save(product);
        return "redirect:/";
    }
}
