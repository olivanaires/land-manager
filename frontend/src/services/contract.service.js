import axios from 'axios';
import authHeader from './auth-header';

const API_URL = '/api/contract/';

class ContractService {
    list() {
        return axios.get(API_URL + 'list', {headers: authHeader()})
    }
}

export default new ContractService();