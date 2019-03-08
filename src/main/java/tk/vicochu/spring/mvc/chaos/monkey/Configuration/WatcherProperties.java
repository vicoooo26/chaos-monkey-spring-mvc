package tk.vicochu.spring.mvc.chaos.monkey.Configuration;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Component
public class WatcherProperties {

    @Value("${chaos.monkey.watcher.controller:false}")
    private boolean controller;

    @Value("${chaos.monkey.watcher.restController:false}")
    private boolean restController;

    @Value("${chaos.monkey.watcher.service:true}")
    private boolean service;

    @Value("${chaos.monkey.watcher.repository:false}")
    private boolean repository;

    @Value("${chaos.monkey.watcher.component:false}")
    private boolean component;
}
