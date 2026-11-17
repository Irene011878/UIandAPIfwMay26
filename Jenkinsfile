
pipeline {

    agent any

    parameters {
        choice(
            name: 'TEST_SUITE',
            choices: ['API', 'UI', 'BOTH'],
            description: 'Select the test suite to execute'
        )
    }

    tools {
        jdk 'JAVA_HOME'
        maven 'Maven 3'
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'master',
                    url: 'https://github.com/Irene011878/UIandAPIfwMay26.git'
            }
        }

        stage('Build & Test') {
            steps {
                script {

                    if (params.TEST_SUITE == 'API') {

                        def apiResult = bat(
                            script: 'mvn clean test -DsuiteXmlFile=src/test/resources/runners/testng-api.xml',
                            returnStatus: true
                        )

                        if (apiResult != 0) {
                            error('API tests failed')
                        }

                    } else if (params.TEST_SUITE == 'UI') {

                        def uiResult = bat(
                            script: 'mvn clean test -DsuiteXmlFile=src/test/resources/runners/testng-ui.xml -Dheadless=true',
                            returnStatus: true
                        )

                        if (uiResult != 0) {
                            error('UI tests failed')
                        }

                    } else if (params.TEST_SUITE == 'BOTH') {

                        def apiResult = bat(
                            script: 'mvn clean test -DsuiteXmlFile=src/test/resources/runners/testng-api.xml',
                            returnStatus: true
                        )

                        def uiResult = bat(
                            script: 'mvn test -DsuiteXmlFile=src/test/resources/runners/testng-ui.xml -Dheadless=true',
                            returnStatus: true
                        )

                        if (apiResult != 0 || uiResult != 0) {
                            error('API and/or UI tests failed')
                        }
                    }
                }
            }

            post {
                always {
                    allure commandline: 'Allure 2',
                           includeProperties: false,
                           jdk: '',
                           resultPolicy: 'LEAVE_AS_IS',
                           results: [[path: 'allure-results']]
                }
            }
        }
    }
}

