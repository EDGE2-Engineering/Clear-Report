package com.easyreport.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.easyreport.app.data.models.Client

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageClientsScreen(
    clients: List<Client>,
    onSave: (Client) -> Unit,
    onDelete: (Client) -> Unit,
    onBack: () -> Unit
) {
    var showAddDialog by remember { mutableStateOf(false) }
    var editingClient by remember { mutableStateOf<Client?>(null) }
    var searchTerm by remember { mutableStateOf("") }

    val filteredClients = clients.filter {
        it.clientName.contains(searchTerm, ignoreCase = true) ||
        it.email.contains(searchTerm, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Manage Clients") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { 
                editingClient = Client()
                showAddDialog = true 
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add Client")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            OutlinedTextField(
                value = searchTerm,
                onValueChange = { searchTerm = it },
                label = { Text("Search Clients") },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) }
            )
            
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(filteredClients) { client ->
                    ClientItem(
                        client = client,
                        onEdit = { 
                            editingClient = it
                            showAddDialog = true 
                        },
                        onDelete = { onDelete(it) }
                    )
                }
            }
        }
    }

    if (showAddDialog && editingClient != null) {
        ClientEditDialog(
            client = editingClient!!,
            onDismiss = { showAddDialog = false },
            onConfirm = { 
                onSave(it)
                showAddDialog = false
            }
        )
    }
}

@Composable
fun ClientItem(client: Client, onEdit: (Client) -> Unit, onDelete: (Client) -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(client.clientName, style = MaterialTheme.typography.titleMedium)
                Text(client.email.ifEmpty { "No Email" }, style = MaterialTheme.typography.bodySmall)
                Text(client.phone.ifEmpty { "No Phone" }, style = MaterialTheme.typography.bodySmall)
            }
            Row {
                IconButton(onClick = { onEdit(client) }) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit")
                }
                IconButton(onClick = { onDelete(client) }) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete", tint = MaterialTheme.colorScheme.error)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientEditDialog(
    client: Client,
    onDismiss: () -> Unit,
    onConfirm: (Client) -> Unit
) {
    var editedName by remember { mutableStateOf(client.clientName) }
    var editedAddress by remember { mutableStateOf(client.clientAddress) }
    var editedEmail by remember { mutableStateOf(client.email) }
    var editedPhone by remember { mutableStateOf(client.phone) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(if (client.clientName.isEmpty()) "Add Client" else "Edit Client") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(value = editedName, onValueChange = { editedName = it }, label = { Text("Name") })
                OutlinedTextField(value = editedAddress, onValueChange = { editedAddress = it }, label = { Text("Address") })
                OutlinedTextField(value = editedEmail, onValueChange = { editedEmail = it }, label = { Text("Email") })
                OutlinedTextField(value = editedPhone, onValueChange = { editedPhone = it }, label = { Text("Phone") })
            }
        },
        confirmButton = {
            Button(onClick = { 
                onConfirm(client.copy(
                    clientName = editedName,
                    clientAddress = editedAddress,
                    email = editedEmail,
                    phone = editedPhone,
                    updatedAt = System.currentTimeMillis()
                ))
            }) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
