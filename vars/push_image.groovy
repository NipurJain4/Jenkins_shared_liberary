def call(ecr_uri){
    sh "docker push ${ecr_uri}"
    echo "✅ Image pushed to ECR"
}