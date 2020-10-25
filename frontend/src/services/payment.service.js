import axios from 'axios';
import authHeader from './auth-header';

const API_URL = '/api/payment/';

class PaymentService {

    byContract(contractId) {
        return axios.get(API_URL + `/by-contract/${contractId}`, {headers: authHeader()});
    }

}

export default new PaymentService();