package kr.hs.dsm.devlib.common.error

abstract class CustomException(
    val errorProperty: ErrorProperty
) : RuntimeException()
