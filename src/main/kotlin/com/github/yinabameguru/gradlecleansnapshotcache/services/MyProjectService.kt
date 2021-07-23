package com.github.yinabameguru.gradlecleansnapshotcache.services

import com.github.yinabameguru.gradlecleansnapshotcache.MyBundle
import com.intellij.openapi.project.Project

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
