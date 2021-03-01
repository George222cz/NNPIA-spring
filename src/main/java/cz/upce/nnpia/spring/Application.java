package cz.upce.nnpia.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Controller
	public class indexController {

		private final CarService carService;

		private final GiftCardService giftCardService;

		public indexController(CarService carService, GiftCardService giftCardService) {
			this.carService = carService;
			this.giftCardService = giftCardService;
		}

		@GetMapping(value = "/")
		public String requestIndex(Model model) {
			model.addAttribute("carService", carService);
			carService.setName("Tesla S");
			carService.setPrice("60 000$");
			model.addAttribute("giftCardService", giftCardService);
			giftCardService.setPrice("40 $");
			return "index";
		}

		@PostMapping("/carPurchased")
		public String carSubmit(Model model) {
			carService.setPurchased();
			model.addAttribute("name", carService.getName());
			model.addAttribute("result", "Purchased!");
			return "result";
		}

		@PostMapping("/giftCardPurchased")
		public String giftSubmit(String name, Model model) {
			giftCardService.setName(name);
			model.addAttribute("name", giftCardService.getName());
			model.addAttribute("result", giftCardService.setPurchased());
			return "result";
		}

	}

	@Controller
	public class secretController {

		private final CounterService counterService;

		public secretController(CounterService counterService) {
			this.counterService = counterService;
		}

		@RequestMapping(value = "/superSecretPage")
		public String getMapping(Model model){
			counterService.add();
			model.addAttribute("counter", counterService.getCounter());
			model.addAttribute("name", "Anonymous");
			return "superSecret";
		}

		@RequestMapping(value = "/superSecretPage/{name}")
		public String getMapping(@PathVariable("name") String name, Model model){
			counterService.add();
			model.addAttribute("counter", counterService.getCounter());
			model.addAttribute("name", name);
			return "superSecret";
		}

	}

}
