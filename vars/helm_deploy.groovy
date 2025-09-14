def call(Map params) {
    /*
      params:
        helmRepoUrl  - Git URL of Helm repo
        helmChartPath - Path to Helm chart inside repo
        imageTag      - New Docker image tag
        gitUser       - Git username for commit
        gitEmail      - Git email for commit
    */

    def helmRepoUrl = params.helmRepoUrl
    def helmChartPath = params.helmChartPath
    def imageTag = params.imageTag
    def gitUser = params.gitUser ?: "Jenkins CI"
    def gitEmail = params.gitEmail ?: "jenkins@example.com"

    sh """
        # Clone Helm repo
        git clone ${helmRepoUrl} helm-repo
        cd helm-repo/${helmChartPath}

        # Update values.yaml with new image tag
        sed -i 's/tag: .*/tag: "${imageTag}"/' values.yaml

        # Configure Git
        git config user.email "${gitEmail}"
        git config user.name "${gitUser}"

        # Commit and push
        git add values.yaml
        git commit -m "Update image tag to ${imageTag}"
        git push origin main
    """
}
