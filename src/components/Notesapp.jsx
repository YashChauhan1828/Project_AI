import React, { useState } from "react";

const NotesApp = () => {
    const [notes, setNotes] = useState([]);
    const [noteText, setNoteText] = useState("");

   
    const addNote = () => {
        if (noteText.trim() !== "") {
            const newNote = {
                id: Date.now(),
                text: noteText,
                timestamp: new Date().toLocaleString(),
            };
            setNotes([newNote, ...notes]); 
            setNoteText(""); 
        }
    };

   
    const deleteNote = (id) => {
        setNotes(notes.filter((note) => note.id !== id));
    };

    return (
        <div className="container">
            <h2>📝 Notes App</h2>
            <div className="input-section">
                <textarea
                    placeholder="Type your note here..."
                    value={noteText}
                    onChange={(e) => setNoteText(e.target.value)}
                />
                <button onClick={addNote} disabled={!noteText.trim()}>Add Note</button>
            </div>
            <div className="notes-list">
                {notes.length === 0 ? (
                    <p>No notes available</p>
                ) : (
                    notes.map((note) => (
                        <div key={note.id} className="note">
                            <p>{note.text}</p>
                            <span className="timestamp">{note.timestamp}</span>
                            <button className="delete-btn" onClick={() => deleteNote(note.id)}>🗑 Delete</button>
                        </div>
                    ))
                )}
            </div>
        </div>
    );
};

export default NotesApp;
