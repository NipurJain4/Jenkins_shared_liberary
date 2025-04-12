def call(imagename){
    sh "docker build -t ${imagenamme} ."
    echo "✅ Docker image built"
}