def call(String region, String accountId) {
    withCredentials([
        string(credentialsId: 'aws-access-key-id', variable: 'AWS_ACCESS_KEY_ID'),
        string(credentialsId: 'aws-secret-access-key', variable: 'AWS_SECRET_ACCESS_KEY')
    ]) {
        sh """
            export AWS_ACCESS_KEY_ID=\$AWS_ACCESS_KEY_ID
            export AWS_SECRET_ACCESS_KEY=\$AWS_SECRET_ACCESS_KEY
            aws ecr get-login-password --region ${region} | docker login --username AWS --password-stdin ${accountId}.dkr.ecr.${region}.amazonaws.com
        """
        echo "✅ Logged into ECR"
    }
}