<template>
    <div>
        <b-row align-h="center">
            <b-card :header="title" header-class="header-title" class="col-md-12">
                <b-card-body>
                    <b-card header="Filtros" class="col-md-12">
                        <b-input type="search" class="filtro" @input="filter = $event"
                                 placeholder="Filtre por número contrato"/>
                    </b-card>
                    <br/>

                    <b-pagination v-model="currentPage" :total-rows="rows"
                                  :per-page="perPage" aria-controls="my-table">
                    </b-pagination>
                    <b-table striped hover bordered show-empty
                             :items="filteredContracts"
                             :fields="fields"
                             :per-page="perPage"
                             :current-page="currentPage"
                             empty-text="Não existe contratos a vencer esse mês">

                        <template v-slot:cell(options)="data">
                            <b-link v-b-modal.show-contract v-on:click="show(data.item.id)" class="option-item"
                                    v-b-tooltip.hover title="Visualizar">
                                <b-icon icon="search"></b-icon>
                            </b-link>
                        </template>
                    </b-table>
                </b-card-body>
            </b-card>
        </b-row>

        <b-modal id="show-contract" title="Visualizar Contrato" centered size="xl" scrollable hide-footer>
            <c-contract-show :contract-id="contractIdToShow"/>
        </b-modal>
    </div>
</template>

<script>
    import moment from 'moment';
    import ContractService from '../../services/contract.service';
    import CContractShow from "../show/Contract";

    export default {
        name: 'contractList',
        components: {CContractShow},
        data() {
            return {
                title: 'Listagem de Contratos',
                perPage: 10,
                currentPage: 1,
                filter: '',
                contracts: [],
                contractIdToShow: null,
                fields: [
                    {
                        key: 'number',
                        label: 'Número',
                        sortable: true,
                        thClass: 'bg-dark text-white'
                    },
                    {
                        key: 'contractdate',
                        label: 'Data',
                        thClass: 'bg-dark text-white',
                        formatter: "formatDate"
                    },
                    {
                        key: 'land.landLocation.name',
                        label: 'Localização',
                        sortable: true,
                        thClass: 'bg-dark text-white'
                    },
                    {
                        key: 'people',
                        label: 'Clientes',
                        sortable: true,
                        thClass: 'bg-dark text-white',
                        formatter: "formatPeople"
                    },
                    {
                        key: 'options',
                        label: 'Opções',
                        thClass: 'center bg-dark text-white',
                        tdClass: 'center',
                    },
                ]
            }
        },
        created() {
            ContractService.list().then(
                response => {
                    this.contracts = response.data;
                });
        },
        computed: {
            rows() {
                return this.contracts.length
            },
            filteredContracts() {
                if (this.filter) {
                    let exp = new RegExp(this.filter.trim(), 'i');
                    return this.contracts.filter(c => exp.test(c.number));
                } else {
                    return this.contracts;
                }
            }
        },
        methods: {
            formatDate(value) {
                if (value) {
                    return moment(String(value), "YYYY-MM-DD").format('DD/MM/YYYY');
                } else {
                    return 'Indefinida';
                }
            },
            formatPeople(value) {
                if (value) {
                    return value.map(p => p['name']).join(', ');
                } else {
                    return 'Indefinido';
                }
            },
            show(id) {
                this.contractIdToShow = id;
            },
        }
    }
</script>

<style scoped>
    .card {
        padding: 0px !important;
        margin-bottom: 10px;
    }

    .header-title {
        font-size: 25px !important;
        text-align: center;
    }
</style>