/*
 * Copyright 2021 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pawga.bpm

import info.novatec.micronaut.camunda.external.client.feature.ExternalTaskSubscription
import jakarta.inject.Singleton
import org.camunda.bpm.client.task.ExternalTask
import org.camunda.bpm.client.task.ExternalTaskHandler
import org.camunda.bpm.client.task.ExternalTaskService
import org.camunda.bpm.engine.variable.Variables
import org.slf4j.Logger
import org.slf4j.LoggerFactory


@Singleton
@ExternalTaskSubscription(topicName = "number-topic")
class SimpleHandler : ExternalTaskHandler {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    override fun execute(externalTask: ExternalTask, externalTaskService: ExternalTaskService) {
        val number = externalTask.getVariable<Int>("number")
        val result = number * 2

        log.info("Completed external task: {}*2={}", number, result)
        externalTaskService.complete(externalTask, Variables.putValue("result", result))
    }
}
