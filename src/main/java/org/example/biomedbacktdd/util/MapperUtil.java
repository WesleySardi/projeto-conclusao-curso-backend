package org.example.biomedbacktdd.util;

import org.example.biomedbacktdd.repositories.mapper.DozerMapper;
import org.springframework.stereotype.Component;

@Component
public class MapperUtil {
    public <T, U> U map(T source, Class<U> targetClass) {
        return DozerMapper.parseObject(source, targetClass);
    }
}

