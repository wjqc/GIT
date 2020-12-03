package com.qc.common.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public abstract class BaseEntity {

    public <T> T toBean(T bean) {

        BeanUtils.copyProperties(this, bean);

        return bean;
    }

    public BaseEntity() {

    }

    public <T> BaseEntity(T bean) {

        BeanUtils.copyProperties(bean, this);
    }

    public static <T> List<T> toBean(List<? extends BaseEntity> list, Class<T> clazz) {

        List<T> result = new ArrayList<>();

        if (!CollectionUtils.isEmpty(list)) {

            try {
                for (BaseEntity entity : list) {

                    T t = clazz.newInstance();

                    result.add(entity.toBean(t));
                }
            } catch (InstantiationException | IllegalAccessException e) {

                log.error("toBean error", e);
            }
        }

        return result;
    }

}
