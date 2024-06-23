import axios from 'axios';

const baseUrl = 'http://localhost:8080/suggestions';

const getRandom = async () => {
    const response = await axios.get(`${baseUrl}/random`);
    return response.data;
};

const suggestionService = { getRandom };
export default suggestionService;