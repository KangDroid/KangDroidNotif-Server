package com.kangdroid.notifserver.domain

import javax.persistence.*

@Entity
class Notification() : BaseTimeEntity() {
    // Constructor
    constructor(reqPackageIn: String = "", titleIn:String = "", contentIn: String = "") : this () {
        this.reqPackage = reqPackageIn
        this.title = titleIn
        this.content = contentIn
    }

    // Identifier
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    // Notification Request Package
    @Column(length = 500, nullable = false)
    var reqPackage: String? = null

    // Notification Name[Title]
    @Column(length = 500, nullable = false)
    var title: String? = null

    // Notification Content
    @Column(nullable = false)
    var content: String? = null
}