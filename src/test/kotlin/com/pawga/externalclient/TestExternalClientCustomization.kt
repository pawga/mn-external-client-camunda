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
package com.pawga.externalclient

import info.novatec.micronaut.camunda.external.client.feature.ExternalClientCustomizer
import io.micronaut.context.annotation.Replaces
import jakarta.inject.Singleton
import org.camunda.bpm.client.ExternalTaskClientBuilder

/**
 * @author Martin Sawilla
 */
@Singleton
@Replaces(ExternalClientCustomizer::class)
class TestExternalClientCustomization: ExternalClientCustomizer {

    override fun customize(builder: ExternalTaskClientBuilder) {
        builder.usePriority(false)
    }
}
