package com.pawga.bpm

import com.pawga.model.Invoice
import info.novatec.micronaut.camunda.external.client.feature.ExternalTaskSubscription
import jakarta.inject.Singleton
import org.camunda.bpm.client.task.ExternalTask
import org.camunda.bpm.client.task.ExternalTaskHandler
import org.camunda.bpm.client.task.ExternalTaskService
import org.camunda.bpm.engine.variable.Variables
import org.camunda.bpm.engine.variable.value.TypedValue
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Created by sivannikov on 22.04.2025 12:07
 */
@Singleton
@ExternalTaskSubscription(topicName = "invoiceArchiver")
class InvoiceArchiverHandler : ExternalTaskHandler {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    override fun execute(externalTask: ExternalTask, externalTaskService: ExternalTaskService) {
        val typedInvoice = externalTask.getVariableTyped<TypedValue>("invoice")
        val invoice = typedInvoice.value as Invoice
        log.info(
            "invoiceArchiver Invoice on process scope archived: {}",
            invoice
        )
        externalTaskService.complete(externalTask)
    }
}