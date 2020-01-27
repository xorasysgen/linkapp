package linkapp

import linkapp.auth.*

class BootStrap {

    def init = { servletContext ->


        def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush:true);
        def userRole = new Role(authority: 'ROLE_USER').save(flush:true);


        /*def admin = new User(username: 'root', password: 'root', firstName: 'anurag', lastName: 'bhaskar',
                email: 'abanurag@gmail.com').save(flush:true)
        def user = new User(username: 'sushil', password: 'sushil', firstName: 'sushil', lastName: 'kumar',
                email: 'sushil.bhaskar@rxlogix.com').save(flush:true)

         UserRole.create(admin, adminRole, true)
         UserRole.create(user, userRole, true)

        UserRole.withSession {
            it.flush()
            it.clear()
        }*/

    }
    def destroy = {
    }
}
