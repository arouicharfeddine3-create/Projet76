# Projet76 - Automation Workflow Engine

## 🎯 Overview

Projet76 is a comprehensive Android automation application that allows users to create, manage, and execute complex workflows with visual node-based editing, integrated browser automation, and advanced scripting capabilities.

## ✨ Features

### 1. Workflow Editor (محرر سير العمل)
- **Infinite Canvas**: Zoomable and pannable drawing area with grid background
- **Node-Based System**: Drag-and-drop node creation from multiple categories
- **Smart Connections**: Visual data flow with type checking and auto-conversion
- **Advanced Editing**: Multi-selection, copy/paste, undo/redo, keyboard shortcuts
- **Expression Editor**: JavaScript/Python expressions with $input, $node, $env variables

### 2. Runner Screen (شاشة التشغيل)
- **Multiple Execution Modes**:
  - Full execution (all nodes)
  - Selective execution (from node X to Y)
  - Step-by-step debug mode with breakpoints
- **Colored Logging**: Real-time execution logs with filtering
- **Scheduling**: Cron expression support with preset options
- **Parallel Queue**: Manage multiple workflow executions
- **Node Inspector**: View node inputs/outputs in tree format

### 3. Workspace (مساحة العمل)
- **File Explorer**: Tree-view file browser with multi-type support
- **Multi-Tab Editor**:
  - Syntax highlighting for 50+ languages
  - JSON/CSV visual editor (table view)
  - Markdown preview
  - Image/PDF/Video viewer
- **File Operations**: Create, rename, copy, move, delete, share, compress
- **Cloud Sync**: Google Drive, Dropbox, S3, WebDAV integration

### 4. Libraries (المكتبات)
- **JavaScript Library**: Script management with versioning
- **Python Library**: Python script management
- **Browser Connectors (Raccords)**: Recorded automation sequences
- **Selectors**: CSS/XPath selector library
- **Community Hub**: Share and discover community components

### 5. Browser (المتصفح المدمج)
- **Full Browser Features**:
  - Navigation, tabs, incognito mode
  - Custom User-Agent, cookie/storage management
- **Automation Tools**:
  - Raccord Recorder: Record interactions (click, input, scroll, wait)
  - Selector Capture Mode: Extract CSS/XPath selectors
  - Quick JavaScript snippets
- **Data Extraction**:
  - File extractor (images, PDFs, documents)
  - HTML table to CSV/JSON converter
  - Structured data extractor
- **Developer Tools**: JavaScript console, network monitor, DOM inspector

### 6. Settings (الإعدادات)
- **Account & Sync**: Cloud storage integration
- **Security**: PIN/Biometric lock, workspace encryption, secrets management
- **Performance**: Node timeout, parallel limit, battery saver mode
- **Customization**: Light/Dark/Auto theme, font size, language (AR/EN)
- **Backup**: Export/import encrypted backup
- **Developer Options**: WebView debugging, system logs, factory reset

## 🔧 Node Types

### Triggers
- Manual
- Webhook (with custom URL)
- Cron (scheduler)
- Browser Event
- File Watcher
- NFC/QR Code

### Data & Network
- HTTP Request (GET/POST/PUT/DELETE with headers and body)
- JavaScript Executor
- Python Executor
- Extract Files

### Browser Automation
- Browser Node (with multiple command types)
- Selector Node (wait, extract, interact)
- Raccord Node (execute recorded sequences)

### Flow Control
- Condition (If/Else)
- Switch
- Loop
- Merge
- Delay/Wait

### Data Processing
- Set Variable
- Log
- Files (Read/Write/List)
- Note/Clipboard

### Navigation
- Go to Screen
- Close Screen
- Save to Screen (global variables)
- Read from Screen
- Show Notification

### AI & Advanced
- AI Node (OpenAI/Gemini)
- Passthrough
- Error Handler

## 🚀 Advanced Features

### Deep Integrations
- **Drag & Drop Between Screens**:
  - Libraries → Canvas: Auto-create nodes
  - Workspace → Canvas: Load file content
  - Browser → Canvas: Add captured selectors/raccords

- **Live Preview**: Monitor browser automation while running workflows
- **Git Integration**: Version control for workspace (optional)
- **Local REST API**: Run workflows via HTTP on port 8080
- **Plugin System**: Load custom node types as .flowplugin packages
- **AI Copilot**: Describe workflows in Arabic, auto-generate nodes

### Data Handling
- Global Variables: Share data between screens
- Expression Editor: JavaScript/Python in any field
- Type Conversion: Smart automatic data type conversion
- Error Paths: Alternative flow on node failure

## 📱 Technical Stack

- **UI**: Jetpack Compose with Material Design 3
- **Architecture**: MVVM with Kotlin Coroutines
- **Database**: Room Database for local persistence
- **Networking**: Retrofit + OkHttp
- **Code Execution**: Rhino (JavaScript), Chaquopy (Python) [optional]
- **WebView**: Android WebKit
- **DI**: Koin
- **Security**: AndroidX Security with encryption
- **Background Tasks**: WorkManager

## 🏗️ Project Structure

```
app/src/main/
├── kotlin/com/automation/
│   ├── ui/
│   │   ├── MainActivity.kt
│   │   ├── runner/
│   │   ├── editor/
│   │   ├── workspace/
│   │   ├── libraries/
│   │   ├── browser/
│   │   ├── settings/
│   │   └── theme/
│   ├── data/
│   │   ├── database/
│   │   ├── models/
│   │   └── repository/
│   ├── domain/
│   │   ├── models/
│   │   ├── usecase/
│   │   └── repository/
│   ├── services/
│   ├── utils/
│   └── AutomationApp.kt
└── res/
    ├── drawable/
    ├── layout/
    ├── values/
    └── xml/
```

## 🔐 Security

- AES-256 encryption for sensitive data
- Biometric authentication support
- Secure credential storage
- SSL certificate validation
- Data isolation per app

## 📊 Performance

- Optimized for devices with 26+ API level
- Efficient graph traversal algorithm
- Parallel execution queue management
- Memory-efficient file handling
- Battery-saver mode support

## 🛠️ Building

```bash
# Clone repository
git clone https://github.com/arouicharfeddine3-create/Projet76.git
cd Projet76

# Build APK
./gradlew assembleDebug

# Run tests
./gradlew test
```

## 📝 License

MIT License - See LICENSE file for details

## 👨‍💻 Author

Created by arouicharfeddine3-create

---

**Version**: 1.0.0 | **Last Updated**: 2026-07-15
