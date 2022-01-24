package com.uju.springbootrestapi.item

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("M")
class Movie: Item() {
    var director: String = ""
    var actor: String = ""
}