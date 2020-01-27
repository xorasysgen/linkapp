package linkapp.model

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured(['ROLE_USER', 'ROLE_ADMIN'])
class SubscriptionController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Subscription.list(params), model:[subscriptionCount: Subscription.count()]
    }

    def show(Subscription subscription) {
        respond subscription
    }

    def create() {
        respond new Subscription(params)
    }

    @Transactional
    def save(Subscription subscription) {
        if (subscription == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (subscription.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond subscription.errors, view:'create'
            return
        }

        subscription.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'subscription.label', default: 'Subscription'), subscription.id])
                redirect subscription
            }
            '*' { respond subscription, [status: CREATED] }
        }
    }

    def edit(Subscription subscription) {
        respond subscription
    }

    @Transactional
    def update(Subscription subscription) {
        if (subscription == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (subscription.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond subscription.errors, view:'edit'
            return
        }

        subscription.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'subscription.label', default: 'Subscription'), subscription.id])
                redirect subscription
            }
            '*'{ respond subscription, [status: OK] }
        }
    }

    @Transactional
    def delete(Subscription subscription) {

        if (subscription == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        subscription.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'subscription.label', default: 'Subscription'), subscription.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'subscription.label', default: 'Subscription'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
