node {
    def mvnHome
   
        stage('Preparation') {
            git 'https://github.com/eliasnogueira/test-automation-javaone-2017.git'
            mvnHome = tool 'M3'
        }
   
        stage('Build') {
            dir('javaone-backend') {
                sh "'${mvnHome}/bin/mvn' clean package" 
            }
        }
   
        stage('Deploy') {
            dir('javaone-backend') {
                sh "'${mvnHome}/bin/mvn' exec:java &"  
            }
        }
  
        stage('API - Smoke') {
            dir('javaone-test-project') {
                sh "'${mvnHome}/bin/mvn' test -Papi-smoke" 
            } 
        }
   
        stage('API - Functional') {
            dir('javaone-test-project') {
                sh "'${mvnHome}/bin/mvn' test -Papi-functional" 
            } 
        }

       stage('Web') {
            dir('javaone-test-project') {
                sh "'${mvnHome}/bin/mvn' test -Pweb-grid"
            }  
        } 
        
       stage('Results') {
           junit '**javaone-backend/target/surefire-reports/TEST-*.xml'
       }
}
