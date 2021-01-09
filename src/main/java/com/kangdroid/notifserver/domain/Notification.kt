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
    var id: Long = 0

    // Notification Request Package
    @Column(length = 500, nullable = false)
    var reqPackage: String = ""

    // Notification Name[Title]
    @Column(length = 500, nullable = false)
    var title: String = ""

    // Notification Content
    @Column(nullable = false)
    var content: String = ""
}