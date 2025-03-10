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
package com.pawga.handler

import info.novatec.micronaut.camunda.external.client.feature.ExternalTaskSubscription
import jakarta.inject.Singleton
import org.camunda.bpm.client.task.ExternalTask
import org.camunda.bpm.client.task.ExternalTaskHandler
import org.camunda.bpm.client.task.ExternalTaskService

/**
 * @author Martin Sawilla
 */
@Singleton
@ExternalTaskSubscription(
    topicName = "test-topic-annotation",
    lockDuration = 19000,
    variables = ["test-one", "test-two"],
    localVariables = true
)
class TestHandlerAnnotation : ExternalTaskHandler {

    override fun execute(externalTask: ExternalTask, externalTaskService: ExternalTaskService) {
        // does nothing
    }
}
