def call(imagename){
    sh "docker build -t ${imagename} ."
    echo "✅ Docker image built"
}