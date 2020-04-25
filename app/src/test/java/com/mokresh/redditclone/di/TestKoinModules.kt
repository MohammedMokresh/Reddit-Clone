package com.mokresh.redditclone.di


import org.koin.core.module.Module

class TestKoinModules {

    companion object {
        fun getModules(): List<Module> {
            return AppKoinModules.getModules()
                .toMutableList()
                .apply {
                    add(testModule)
                }
        }
    }
}