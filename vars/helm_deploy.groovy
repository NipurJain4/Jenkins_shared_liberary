def call(String helmRepoUrl, String gitUser = "NipurJain4", String gitEmail = "nipurjain.tmu.cs@gmail.com") {
   sh"""
   export image_tag=v_${GIT_COMMIT}_${BUILD_ID}
   git clone ${helmRepoUrl}
   cd DevOps-Task-Swayatt-helm_chart
   sed -i 's/^  tag:.*$/  tag: "${image_tag}"/' values.yaml
    # Configure git
        git config user.name "${gitUser}"
        git config user.email "${gitEmail}"
          # Commit and push the change
        git add values.yaml
        git commit -m "Update Docker image tag to ${image_tag}"
        git push origin main
   """
}
