package linkapp

import grails.plugin.springsecurity.annotation.Secured


@Secured(['ROLE_USER', 'ROLE_ADMIN'])
class MainController {

    /*https://guides.grails.org/creating-your-first-grails-app/guide/index.html*/
    def index() {

        def userName="Grails UserName SUSHIL"
        [user: userName]
    }
}
