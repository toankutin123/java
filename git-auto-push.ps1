param(
    [string]$RemoteUrl = "https://github.com/toankutin123/java.git",
    [string]$Branch = "main",
    [string]$CommitMessage = "Auto commit from git-auto-push.ps1"
)

$ErrorActionPreference = "Stop"

if (-not (Get-Command git -ErrorAction SilentlyContinue)) {
    Write-Error "Git is not installed or not available in PATH."
    exit 1
}

$workspaceRoot = Resolve-Path .
Write-Output "Workspace root: $workspaceRoot"

if (-not (Test-Path ".git")) {
    Write-Output "Initializing a new Git repository..."
    git init
}

$existingRemote = $null
try {
    $existingRemote = git remote get-url origin 2>$null
} catch {
    # origin does not exist yet
}

if (-not $existingRemote) {
    Write-Output "Adding origin remote: $RemoteUrl"
    git remote add origin $RemoteUrl
} elseif ($existingRemote -ne $RemoteUrl) {
    Write-Output "Updating origin remote from $existingRemote to $RemoteUrl"
    git remote remove origin
    git remote add origin $RemoteUrl
}

$currentBranch = git branch --show-current 2>$null
if (-not $currentBranch) {
    Write-Output "Creating and switching to branch '$Branch'..."
    git checkout -b $Branch
} elseif ($currentBranch -ne $Branch) {
    Write-Output "Switching to branch '$Branch'..."
    git checkout $Branch 2>$null
    if ($LASTEXITCODE -ne 0) {
        git checkout -b $Branch
    }
}

$status = git status --porcelain
if (-not $status) {
    Write-Output "No changes detected. Nothing to commit."
} else {
    Write-Output "Staging changes..."
    git add -A
    if (-not (git diff --cached --quiet)) {
        Write-Output "Committing changes..."
        git commit -m $CommitMessage
    } else {
        Write-Output "No staged changes to commit."
    }
}

Write-Output "Pushing to origin/$Branch..."
git push -u origin $Branch
