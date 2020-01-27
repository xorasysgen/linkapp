package linkapp.model

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured(['ROLE_USER', 'ROLE_ADMIN'])
class ReadingItemController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ReadingItem.list(params), model:[readingItemCount: ReadingItem.count()]
    }

    def show(ReadingItem readingItem) {
        respond readingItem
    }

    def create() {
        respond new ReadingItem(params)
    }

    @Transactional
    def save(ReadingItem readingItem) {
        if (readingItem == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (readingItem.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond readingItem.errors, view:'create'
            return
        }

        readingItem.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'readingItem.label', default: 'ReadingItem'), readingItem.id])
                redirect readingItem
            }
            '*' { respond readingItem, [status: CREATED] }
        }
    }

    def edit(ReadingItem readingItem) {
        respond readingItem
    }

    @Transactional
    def update(ReadingItem readingItem) {
        if (readingItem == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (readingItem.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond readingItem.errors, view:'edit'
            return
        }

        readingItem.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'readingItem.label', default: 'ReadingItem'), readingItem.id])
                redirect readingItem
            }
            '*'{ respond readingItem, [status: OK] }
        }
    }

    @Transactional
    def delete(ReadingItem readingItem) {

        if (readingItem == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        readingItem.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'readingItem.label', default: 'ReadingItem'), readingItem.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'readingItem.label', default: 'ReadingItem'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
