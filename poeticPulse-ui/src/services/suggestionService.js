import axios from 'axios';

const baseUrl = process.env.REACT_APP_BACKEND_BASEURL + "/suggestions";

const getRandom = async () => {
    const response = await axios.get(`${baseUrl}/random`);
    return response.data;
};

const suggestionService = { getRandom };
export default suggestionService;