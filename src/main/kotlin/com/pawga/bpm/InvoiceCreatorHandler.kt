package com.pawga.bpm

import com.pawga.model.Invoice
import info.novatec.micronaut.camunda.external.client.feature.ExternalTaskSubscription
import jakarta.inject.Singleton
import org.camunda.bpm.client.task.ExternalTask
import org.camunda.bpm.client.task.ExternalTaskHandler
import org.camunda.bpm.client.task.ExternalTaskService
import org.camunda.bpm.client.variable.ClientValues
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.HashMap
import java.util.Random

/**
 * Created by sivannikov on 22.04.2025 12:03
 */
@Singleton
@ExternalTaskSubscription(topicName = "invoiceCreator")
class InvoiceCreatorHandler : ExternalTaskHandler {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    override fun execute(externalTask: ExternalTask, externalTaskService: ExternalTaskService) {
        // instantiate an invoice object
        val invoice = Invoice("ABC-" + Random().nextInt(16))

        // create an object typed variable with the serialization format XML
        val invoiceValue = ClientValues
            .objectValue(invoice)
            .serializationDataFormat("application/json")
            .create()

        // add the invoice object and its id to a map
        val variables: MutableMap<String, Any> = HashMap()
        variables["invoiceId"] = invoice.id ?: "empty"
        variables["invoice"] = invoiceValue
        // select the scope of the variables
        externalTaskService.complete(externalTask, variables)
        log.info("invoiceCreator The External Task {} has been completed!", externalTask.id)
    }
}