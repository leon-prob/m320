package ch.noseryoung.blj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan; // <--- Import this!

@SpringBootApplication
@EntityScan(basePackages = {
        "ch.noseryoung.blj.car",
        "ch.noseryoung.blj.garage",
        "ch.noseryoung.blj.loan",
        "ch.noseryoung.blj.login.user"
})

public class CarGarageApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarGarageApplication.class, args);
    }

}
