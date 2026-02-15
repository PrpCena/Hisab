package com.prp.Hisab.common.config;


import org.mapstruct.InjectionStrategy;
import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@MapperConfig(componentModel = "spring", // makes every mapper @Component
        unmappedTargetPolicy = ReportingPolicy.ERROR, // reports missing field from the source
        injectionStrategy = InjectionStrategy.FIELD)
public interface GlobalMapperConfig {
}
