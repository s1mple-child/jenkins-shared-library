#! /usr/bin/env groovy

package workflowlibs.manager;

import groovy.text.StreamingTemplateEngine

def emailTemplate(params) {

    def fileContents = libraryResource 'html/email.html'
    def engine = new StreamingTemplateEngine()

    return engine.createTemplate(fileContents).make(params).toString()
}

def notifyEmail(buildStatus, emailRecipients) {

    try {
        def statusSuccess = true
        def hasArtifacts = true

        if(buildStatus != "SUCCESS") {
            statusSuccess = false
            hasArtifacts = false
        }

        def body = emailTemplate([
                "jenkinsText"   :   env.JOB_NAME,
                "jenkinsUrl"    :   env.BUILD_URL,
                "statusSuccess" :   statusSuccess,
                "hasArtifacts"  :   hasArtifacts,
                "downloadUrl"   :   "www.downloadurl.com"
        ]);

        emailext body: body,
                to: emailRecipients,
                subject: "[ ${env.JOB_NAME} ] [${env.BUILD_NUMBER}] - ${buildStatus} ",
                mimeType: 'text/html'

    } catch (e){
        println "ERROR SENDING EMAIL ${e}"
    }
}
