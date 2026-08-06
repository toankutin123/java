# Git Auto Push Helper

This workspace includes a helper script and a VS Code task to initialize Git and push changes to GitHub.

## Usage

1. Add your Java project files inside this workspace.
2. Run the VS Code task:
   - `Terminal` -> `Run Task...` -> `Auto Push to GitHub`

Or run the script directly in PowerShell:

```powershell
cd D:\learning_java\luuthetoan
powershell -NoProfile -ExecutionPolicy Bypass -File .\git-auto-push.ps1
```

## What it does

- Initializes a Git repository if one does not exist
- Sets `origin` to `https://github.com/toankutin123/java.git`
- Adds and commits changed files
- Pushes to `main`

> Note: VS Code cannot reliably execute this automatically on window open without an extension or custom environment. Use the task or script on startup.
