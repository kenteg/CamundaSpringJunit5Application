package ru.raif.camundaspringjunit5

import org.camunda.bpm.engine.test.Deployment
import org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests
import org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.assertThat
import org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.complete
import org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.execute
import org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.job
import org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.processInstanceQuery
import org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.runtimeService
import org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.task
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest


@Deployment(resources = ["hello_world_process"])
@SpringBootTest
class HelloWorldProcessTest {

    @Test
    fun `process test runned from junit5 with spring no problem`(){

        val processInstance = runtimeService().startProcessInstanceByKey("hello_world_process")
        // Then it should be active
        assertThat(processInstance).isActive
        // And it should be the only instance
        assert(processInstanceQuery().count()==1L)
        // And there should wait in our delegate
        assertThat(processInstance).isWaitingAt("HelloWorldDelegate")
        //Run current service task
        execute(job())
        // Then the process instance should be ended
        assertThat(processInstance).isEnded

    }
}