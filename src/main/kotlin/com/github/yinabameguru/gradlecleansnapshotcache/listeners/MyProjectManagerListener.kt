package com.github.yinabameguru.gradlecleansnapshotcache.listeners

import com.github.yinabameguru.gradlecleansnapshotcache.services.MyProjectService
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManagerListener

internal class MyProjectManagerListener : ProjectManagerListener {

    override fun projectOpened(project: Project) {
        project.service<MyProjectService>()
    }
}
