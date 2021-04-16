package ru.raif.camundaspringjunit5

import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.springframework.stereotype.Component


@Component
class HelloWorldDelegate: JavaDelegate {
    override fun execute(execution: DelegateExecution?) {
        println("Hello world!!!!!!!!!!111")
    }
}