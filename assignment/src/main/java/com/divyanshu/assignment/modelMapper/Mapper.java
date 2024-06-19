package com.divyanshu.assignment.modelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    @Bean
    public ModelMapper mopdelMapper(){
        return new ModelMapper();
    }
}
