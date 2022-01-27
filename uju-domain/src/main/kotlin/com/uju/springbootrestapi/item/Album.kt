package com.uju.springbootrestapi.item

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("A")
class Album : Item() {
    var artist: String = ""
    var etc: String = ""
}
