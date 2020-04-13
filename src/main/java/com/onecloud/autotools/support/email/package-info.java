/**
 Contains components that allow the system to transmit Email messages through
 an external SMTP server.  The PostOffice interface defines the means through which
 client classes can submit messages for transmission while participating in the
 overarching transactional context of request processing.  Email transmissions
 are deferred until the work context reaches a commit point.
 */
package com.onecloud.autotools.support.email;