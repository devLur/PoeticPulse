import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import poemService from '../services/poemService';
import suggestionService from '../services/suggestionService';
import '../styles/PoemPage.css';

function PoemPage() {
    const { id } = useParams();
    const [title, setTitle] = useState('');
    const [lines, setLines] = useState([]);
    const [suggestionTitle, setSuggestionTitle] = useState('');
    const [suggestionLines, setSuggestionLines] = useState([]);
    const navigate = useNavigate();

    const fetchPoem = async (id) => {
        const data = await poemService.get(id);
        setTitle(data.title);
        setLines(data.lines || [{ author: '', line: '', title: '' }]);
    };

    const fetchSuggestion = async () => {
        const data = await suggestionService.getRandom();
        setSuggestionTitle(data.title);
        setSuggestionLines(data.lines || [{ author: '', line: '', title: '' }]);
    };

    useEffect(() => {
        if (id) {
            fetchPoem(id).catch(error => console.log(error));
        }
        if (suggestionLines.length === 0) {
            fetchSuggestion().catch(error => console.log(error));
        }
    }, [id, suggestionLines.length]);

    const handleLineChange = (index, field, value) => {
        setLines(lines.map((line, i) => {
            if (i === index) {
                return { ...line, [field]: value };
            } else {
                return line;
            }
        }));
    };

    const handleLineDelete = (index) => {
        setLines(lines.filter((_, i) => i !== index));
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        const poemData = {
            title: title,
            lines: lines
        };
        try {
            await (id ? poemService.update(id, poemData) : poemService.create(poemData));
            navigate('/');
        } catch (error) {
            throw new Error(id ? 'Failed to update poem' : 'Failed to create poem');
        }
    };

    const addSuggestionLineToPoem = (suggestionLine) => {
        const newLine = {
            author: suggestionLine.author,
            line: suggestionLine.line,
            title: suggestionLine.title,
        };
        setLines([...lines, newLine]);
    };

    return (
        <div>
            <div class="poemBox">
                <div class="title">Your poem:</div>
                <form onSubmit={handleSubmit}>
                    <label>
                        Title:
                        <input type="text" value={title} onChange={e => setTitle(e.target.value)} required />
                    </label>
                    {lines.map((lineItem, index) => (
                        <div key={index}>
                            <label>
                                Author:
                                <input type="text" value={lineItem.author} onChange={e => handleLineChange(index, 'author', e.target.value)} required />
                            </label>
                            <label>
                                Line:
                                <input type="text" value={lineItem.line} onChange={e => handleLineChange(index, 'line', e.target.value)} required />
                            </label>
                            <label>
                                Title:
                                <input type="text" value={lineItem.title} onChange={e => handleLineChange(index, 'title', e.target.value)} required />
                            </label>
                            <button type="button" onClick={() => handleLineDelete(index)}>Delete</button>
                        </div>
                    ))}
                    <button type="submit">{id ? 'Update' : 'Create'}</button>
                    <button type="button" onClick={() => navigate('/')}>Cancel</button>
                </form>
            </div>
            <div class="suggestionBox">
            <div class="title">Famous suggestion:</div>
                    <div>Titel: {suggestionTitle} <button type="button" onClick={() => fetchSuggestion()}>Next Suggestion</button></div>
                    <table>
                        {suggestionLines.map((lineItem, index) => (
                            <tr key={index}>
                                <td>{lineItem.line}</td>
                                <td><button onClick={() => addSuggestionLineToPoem(lineItem)}>Add to poem</button></td>
                            </tr>
                        ))}
                    </table>
            </div>
        </div>
    );
}

export default PoemPage;