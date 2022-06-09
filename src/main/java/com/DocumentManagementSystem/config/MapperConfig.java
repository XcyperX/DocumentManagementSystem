package com.DocumentManagementSystem.config;

import com.DocumentManagementSystem.dto.DocumentDto;
import com.DocumentManagementSystem.dto.FileModelDto;
import com.DocumentManagementSystem.dto.UserDto;
import com.DocumentManagementSystem.model.Document;
import com.DocumentManagementSystem.model.FileModel;
import com.DocumentManagementSystem.model.User;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import net.rakugakibox.spring.boot.orika.OrikaMapperFactoryConfigurer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Configuration
public class MapperConfig implements OrikaMapperFactoryConfigurer {

    @Override
    public void configure(MapperFactory mapperFactory) {
        mapperFactory.classMap(Document.class, DocumentDto.class)
                .byDefault()
                .register();

        mapperFactory.classMap(User.class, UserDto.class)
                .byDefault()
                .register();

        mapperFactory.classMap(FileModel.class, FileModelDto.class)
                .byDefault()
                .register();
    }
}
