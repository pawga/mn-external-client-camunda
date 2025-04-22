package com.pawga.bpm

import info.novatec.micronaut.camunda.external.client.feature.ExternalTaskSubscription
import io.micronaut.context.annotation.Bean
import jakarta.inject.Singleton
import org.camunda.bpm.client.interceptor.ClientRequestContext
import org.camunda.bpm.client.interceptor.ClientRequestInterceptor
import org.camunda.bpm.client.task.ExternalTask
import org.camunda.bpm.client.task.ExternalTaskHandler
import org.camunda.bpm.client.task.ExternalTaskService
import org.camunda.bpm.engine.variable.Variables
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Created by sivannikov on 22.04.2025 12:11
 */
@Singleton
@ExternalTaskSubscription(topicName = "requestRejecter")
class RequestRejecter : ExternalTaskHandler {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    override fun execute(externalTask: ExternalTask, externalTaskService: ExternalTaskService) {
        val number = externalTask.getVariable<Int>("number")
        val result = number * 2

        log.info("Completed external task: {}*2={}", number, result)
        externalTaskService.complete(externalTask, Variables.putValue("result", result))
    }

    @Bean
    fun interceptor(): ClientRequestInterceptor {
        return ClientRequestInterceptor { context: ClientRequestContext ->
            log.info("Request interceptor called!")
            context.addHeader("X-MY-HEADER", "External Tasks Rock!")
        }
    }
}