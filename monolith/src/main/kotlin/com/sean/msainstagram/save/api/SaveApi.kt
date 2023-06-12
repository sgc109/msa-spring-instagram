package com.sean.msainstagram.save.api

import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "Save API")
interface SaveApi {
    fun save()

    fun unsave()
}
