package com.example.vktestgooglecast

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val castModule = module {
    viewModel { CastViewModel() }
}