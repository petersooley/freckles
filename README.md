## Freckles

Playground for all things lagom, akka, docker, cassandra, kafka, ...

### Goals

- [ ] Start with a basic lagom project
- [ ] Move cassandra into a docker container and link to dev project
- [ ] Move kafka into a docker container and link to dev project
- [ ] Build services into docker containers
- [ ] Create the contacts service (below)
- [ ] Run the whole thing on minikube
- [ ] Come up with more goals

### Contact Service

Most of these are specific to a single user. User's are just specified with an ID. Contacts may be specified as a user ID or just an email address. (Some contacts may not be in the system yet and won't have a user ID. So we need to listen for `UserEstablished` and grab the user's ID by the established email address.)

**Commands**

* `AddContact` - Associate a contact with a user even if it's just an email address
* `RemoveContact` - Remove the contact for a user
* `UpdateContact` - Update the email address of the contact for a user (this does not need to match the associated user's actual address, but that may be something we want to update in the user's list of valid email addresses, see `EmailFound`)

**Queries**

* `AllContacts` - Paginated; lists email addresses and associated user accounts and general status of established or not

**Events Received**

* `UserEstablished` - As in, the user has validated their email or have otherwise completed the sign up process
* `UserDeleted` - We'll need to disassociate contacts from user these user accounts (but the contact relationship should remain)

**Events Sent**

* `EmailFound` - Whenever there's a hint that an email might belong to a certain user this is sent to add that address to the user's list of potential emails. This can also happen when a user answers an email (i.e. an invitation) sent from another user through our app that is different than the email they are logged into the app with. Like if Linda invites `bob13@blah.com` but according to our app he's just logged in as `bob14@blah.com` , then by answering that email we can figure that he is the owner of both addresses. 
* `ContactAssociated` - After `AddContact` 
* `ContactDisassociated` - After `RemoveContact` or `UserDeleted`

